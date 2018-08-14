/**
* Generated By NPCScript :: A scripting engine created for openrsc by Zilent
*/
package org.openrsc.server.npchandler.Imp_Catcher;

import org.openrsc.server.Config;
import org.openrsc.server.event.SingleEvent;
import org.openrsc.server.logging.Logger;
import org.openrsc.server.logging.model.eventLog;
import org.openrsc.server.model.*;
import org.openrsc.server.event.DelayedQuestChat;
import org.openrsc.server.npchandler.NpcHandler;
import org.openrsc.server.util.DataConversions;
public class Wizard_Mizgog implements NpcHandler {

	private final void questNotStarted(final Npc npc, final Player owner) {
		final String[] messages0 = {"Hello there"};
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages0, true) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options0 = {"Give me a quest!", "Most of your friends are pretty quiet, aren't they?"};
						owner.setBusy(false);
						owner.sendMenu(options0);
						owner.setMenuHandler(new MenuHandler(options0) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										final String[] messages1 = {"Give me a quest what?"};
										World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages1) {
											public void finished() {
												World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
													public void action() {
														final String[] options1 = {"Give me a quest please", "Give me a quest or else", "Just stop messing around and give me a quest"};
														owner.setBusy(false);
														owner.sendMenu(options1);
														owner.setMenuHandler(new MenuHandler(options1) {
															public void handleReply(final int option, final String reply) {
																owner.setBusy(true);
																for(Player informee : owner.getViewArea().getPlayersInView()) {
																	informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
																}
																switch(option) {
																	case 0:
																		final String[] messages2 = {"Well seeing as you asked nicely", "I could do with some help", "The wizard grayzag next door decided he didn't like me", "So he cast a spell of summoning", "And summed hundreds of little imps", "These imps stole all sorts of my things", "Most of these things I don't really care about", "They're just eggs and balls of string and things", "But they stole my 4 magical beads", "There was a red one, a yellow one, a black one, and a white one", "These imps have now spread out all over the kingdom", "Could you get my beads back for me"};
																		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages2) {
																			public void finished() {
																				final String[] messages3 = {"I'll try"};
																				World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, messages3) {
																					public void finished() {
																						owner.addQuest(Quests.IMP_CATCHER, 1);
																						owner.setBusy(false);
																						npc.unblock();
																					}
																				});
																			}
																		});
																		break;
																	case 1:
																		final String[] messages4 = {"Or else what? You'll attack me?", "Hahaha"};
																		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages4) {
																			public void finished() {
																				owner.setBusy(false);
																				npc.unblock();
																			}
																		});
																		break;
																	case 2:
																		final String[] messages5 = {"Ah now you're assuming I have one to give"};
																		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages5) {
																			public void finished() {
																				owner.setBusy(false);
																				npc.unblock();
																			}
																		});
																		break;
																}
															}
														});
													}
												});
											}
										});
										break;
									case 1:
										final String[] messages6 = {"Yes they've mostly got their heads in the clouds", "Thinking about magic"};
										World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages6) {
											public void finished() {
												owner.setBusy(false);
												npc.unblock();
											}
										});
										break;
								}
							}
						});
					}
				});
			}
		});
	}

	private final void questFinished(final Npc npc, final Player owner){
		final String[] options0 = {"Got any more quests?", "Most of your friends are pretty quiet aren't they?"};
		owner.setBusy(false);
		owner.sendMenu(options0);
		owner.setMenuHandler(new MenuHandler(options0) {
			public void handleReply(final int option, final String reply) {
				owner.setBusy(true);
				for(Player informee : owner.getViewArea().getPlayersInView()) {
					informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
				}
				switch(option) {
					case 0:
						moreQuests(npc, owner);
						break;
					case 1:
						final String[] messages6 = {"Yes they've mostly got their heads in the clouds", "Thinking about magic"};
						World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages6) {
							public void finished() {
								owner.setBusy(false);
								npc.unblock();
							}
						});
						break;
				}
			}
		});
	}

	private final void moreQuests(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"No everything is good with the world today"}) {
			public void finished() {
				owner.setBusy(false);
				npc.unblock();
			}
		});
	}
	
	private final void questUnderway(final Npc npc, final Player owner){
		final String[] messages0 = {"So how are you doing finding my beads?"};
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages0, true) {
			public void finished() {
				if(owner.getInventory().contains(231) && owner.getInventory().contains(232) &&  owner.getInventory().contains(233) &&  owner.getInventory().contains(234)) {
					final String[] messages1 = {"I've got all four beads", "It was hard work I can tell you"};
					World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, messages1) {
						public void finished() {
							final String[] messages2 = {"Give them here and I'll sort out a reward"};
							World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages2) {
								public void finished() {
									World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
										public void action() {
											owner.sendMessage("You give the four coloured beads to Wizard Mizgog");
											owner.getInventory().remove(new InvItem(231, 1));
											owner.getInventory().remove(new InvItem(232, 1));
											owner.getInventory().remove(new InvItem(233, 1));
											owner.getInventory().remove(new InvItem(234, 1));
											owner.sendInventory();
											final String[] messages3 = {"Here's your reward then", "An amulet of accuracy"};
											World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages3) {
												public void finished() {
													World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
														public void action() {
															owner.sendMessage("The wizard hands you an amulet");
															owner.getInventory().add(new InvItem(235, 1));
															owner.sendInventory();
															World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
																public void action() {
																	owner.finishQuest(Quests.IMP_CATCHER);
																	owner.sendMessage("Well done. You have completed the Imp catcher quest");
																	owner.sendMessage("@gre@You have gained 1 quest point");
																	owner.incQuestExp(Skills.MAGIC, 1000);
																	owner.setBusy(false);
																	npc.unblock();
																	Logger.log(new eventLog(owner.getUsernameHash(), owner.getAccount(), owner.getIP(), DataConversions.getTimeStamp(), "<strong>" + owner.getUsername() + "</strong>" + " has completed the <span class=\"recent_quest\">Imp Catcher</span> quest!"));
																}
															});
														}
													});
												}
											});
										}
									});
								}
							});
						}
					});
				} else if(owner.getInventory().contains(231) || owner.getInventory().contains(232) ||  owner.getInventory().contains(233) ||  owner.getInventory().contains(234)) {
					final String[] messages0 = {"I have found some of your beads"};
					World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, messages0) {
						public void finished() {
							final String[] messages1 = {"Come back when you have them all", "The four colours of beads I need", "Are red, yellow, black, and white", "Go chase some imps"};
							World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages1) {
								public void finished() {
									owner.setBusy(false);
									npc.unblock();
								}
							});
						}
					});
				} else {
					final String[] messages0 = {"I've not found any yet"};
					World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, messages0) {
						public void finished() {
							final String[] messages1 = {"Well get on with it", "I've lost a white bead, a red bead, a black bead, and a yellow bead", "Go kill some imps"};
							World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, messages1) {
								public void finished() {
									owner.setBusy(false);
									npc.unblock();
								}
							});
						}
					});
				}
			}
		});
	}
	
	public void handleNpc(final Npc npc, final Player owner) throws Exception {
		npc.blockedBy(owner);
		owner.setBusy(true);
		Quest q = owner.getQuest(Quests.IMP_CATCHER);
		if(q != null) {
			if(q.finished()) {
				questFinished(npc, owner);
			} else {
				questUnderway(npc, owner);
			}
		} else {
			questNotStarted(npc, owner);
		}
	}
}