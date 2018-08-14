/**
* Generated By NPCScript :: A scripting engine created for openrsc by Zilent
*/
package org.openrsc.server.npchandler.Lost_City;
import org.openrsc.server.Config;
import org.openrsc.server.event.DelayedQuestChat;
import org.openrsc.server.event.SingleEvent;
import org.openrsc.server.model.ChatMessage;
import org.openrsc.server.model.MenuHandler;
import org.openrsc.server.model.Npc;
import org.openrsc.server.model.Player;
import org.openrsc.server.model.Quest;
import org.openrsc.server.model.Quests;
import org.openrsc.server.model.World;
import org.openrsc.server.npchandler.NpcHandler;



public class Leprachaun implements NpcHandler {
	
	public void handleNpc(final Npc npc, final Player owner) throws Exception {
		npc.blockedBy(owner);
		owner.setBusy(true);
		Quest q = owner.getQuest(Quests.LOST_CITY);
		if(q != null) {
			if(q.finished()) {
				finished(npc, owner);
			} else {
				switch(q.getStage()) {
					case 2:
						questStarted(npc, owner);
						break;
					case 3:
						info(npc, owner);
						break;
					default:
						noQuestStarted(npc, owner);
				}
			}
		} else {
			noQuestStarted(npc, owner);
		}
	}
	

	private void questStarted(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Oi you big oaf!", "Don't cut down that tree!"}, true) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options107 = {"Who are you?", "Hi, Do you know where zanaris is?", "Why not?"};
						owner.setBusy(false);
						owner.sendMenu(options107);
						owner.setMenuHandler(new MenuHandler(options107) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										whoAreYou(npc, owner);
										break;
									case 1:
										quest(npc, owner);
										break;
									case 2:
										whyNot(npc, owner);
										break;
								}
							}
						});
					}
				});
			}
		});
	}
	
	
	private void whoAreYou(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"I am a leprachaun", "what do you want?"}) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options107 = {"Do you know where zanaris is?", "Nothing, goodbye"};
						owner.setBusy(false);
						owner.sendMenu(options107);
						owner.setMenuHandler(new MenuHandler(options107) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										quest(npc, owner);
										break;
									case 1:
										noQuestStarted(npc, owner);
										break;
								}
							}
						});
					}
				});
			}
		});
	}
	
	private void whyNot(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Because thats my home", "silly giant", "now what do you want?"}) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options107 = {"Do you know where zanaris is?", "Nothing, goodbye"};
						owner.setBusy(false);
						owner.sendMenu(options107);
						owner.setMenuHandler(new MenuHandler(options107) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										quest(npc, owner);
										break;
									case 1:
										noQuestStarted(npc, owner);
										break;
								}
							}
						});
					}
				});
			}
		});
	}
	
	private void quest(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Yes I used to live in zanaris", "You will need a dramen staff", "Then all you need to do is walk into that shed", "in lumbridge swamp"}) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options107 = {"How can I get a dramen staff?", "This sounds like a joke, goodbye"};
						owner.setBusy(false);
						owner.sendMenu(options107);
						owner.setMenuHandler(new MenuHandler(options107) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										quest1(npc, owner);
										break;
									case 1:
										noQuestStarted(npc, owner);
										break;
								}
							}
						});
					}
				});
			}
		});
	}
	
	private void info(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"hello giant", "are you ready to go to port sarim?"}, true) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options107 = {"Yes please", "No don't teleport me, i'm not ready"};
						owner.setBusy(false);
						owner.sendMenu(options107);
						owner.setMenuHandler(new MenuHandler(options107) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										teleport1(npc, owner);
										break;
									case 1:
										noTeleport1(npc, owner);
										break;
								}
							}
						});

					}
				});
			}
		});
	}
	
	private void teleport1(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Ok here I go"}) {
			public void finished() {
				owner.sendMessage("The Leprachaun starts chanting softly...");
				owner.teleport(270, 658, false);
				owner.sendMessage("...you end up in port sarim");
				owner.setBusy(false);
				npc.unblock();
				World.unregisterEntity(npc);
			}
		});
	}
	
	private void noTeleport1(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Ok talk to me when you are ready"}) {
			public void finished() {
				owner.setBusy(false);
				npc.unblock();
				World.unregisterEntity(npc);
			}
		});
	}
	
	
	private void quest1(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You will need to craft it from scratch with a chisel", "The only dramen tree left in the world", "is located in a cave on a holy island called entrana", "I can't teleport you directly there", "because it is protected by saradomin", "but I can teleport you to port sarim"}) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options107 = {"Yes please", "No don't teleport me, i'm not ready"};
						owner.setBusy(false);
						owner.sendMenu(options107);
						owner.setMenuHandler(new MenuHandler(options107) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										teleport(npc, owner);
										break;
									case 1:
										noTeleport(npc, owner);
										break;
								}
							}
						});
					}
				});
			}
		});
	}
	

	private void teleport(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Ok here I go"}) {
			public void finished() {
				owner.sendMessage("The Leprachaun starts chanting softly...");
				owner.teleport(270, 658, false);
				owner.sendMessage("...you end up in port sarim");
				owner.incQuestCompletionStage(Quests.LOST_CITY);
				owner.setBusy(false);
				npc.unblock();
				World.unregisterEntity(npc);
			}
		});
	}
		
	private void noTeleport(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Ok talk to me when you are ready"}) {
			public void finished() {
				owner.incQuestCompletionStage(Quests.LOST_CITY);
				owner.setBusy(false);
				npc.unblock();
				World.unregisterEntity(npc);
			}
		});
	}
	
	private void noQuestStarted(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Leave me alone stinky giant!"}) {
			public void finished() {
				owner.setBusy(false);
				npc.unblock();
			}
		});
	}
	
	private void finished(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Leave me alone stinky giant!"}) {
			public void finished() {
				owner.setBusy(false);
				npc.unblock();
			}
		});
	}
}